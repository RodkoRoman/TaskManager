package org.rodko.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.rodko.taskmanager.model.dto.TaskDto;
import org.rodko.taskmanager.model.entity.TaskEntity;
import org.rodko.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JdbcTaskService implements TaskService {

    private final JdbcTaskService jdbcTaskService;

    Connection connection; //Нужно закрывать???

    {
        try {
            String URL = "jdbc:postgresql://localhost:5433/task_manager";
            String USERNAME = "postgres";
            String PASSWORD = "postgres";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных! Возможные причины: \n" +
                    "Неверный URL адрес \n" +
                    "Неверный логин \n" +
                    "Неверный пароль \n" + e.getMessage());
            System.exit(-1);
        }
    }


    @Override
    @Transactional
    public TaskEntity createTask(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        UUID uuid = UUID.randomUUID();
        try {
//            Statement statement = connection.createStatement();
            String sqlCreate = "INSERT INTO task_manager (id, name, description, createAt, isDeleted) " +
                    "Values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreate);
            preparedStatement.setObject(1, uuid);
            preparedStatement.setString(2, taskDto.getName());
            preparedStatement.setString(3, taskDto.getDescription());
            preparedStatement.setObject(4, Instant.now());
            preparedStatement.setBoolean(5, false);

//            String SQL = "INSERT INTO task_manager VALUES("
//                    + uuid + ", "
//                    + "'" + taskDto.getName() + "'" + ", "
//                    + "'" + taskDto.getDescription() + "'" + ", "
//                    + Instant.now() + ", "
//                    + "FALSE)";
//            statement.executeUpdate(SQL);
            Statement statement = connection.createStatement(); // тут создал объект statement т.к. в описании метода
            // executeQuery есть примечание о запрете использования prepareStatement
            String sqlSelectById = "SELECT id FROM task_manager WHERE id = " + uuid; //может стоит вынести за {}?
            ResultSet resultSet = statement.executeQuery(sqlSelectById);

            while (resultSet.next()) {
                taskEntity.setId(resultSet.getObject("id", UUID.class));
                taskEntity.setName(resultSet.getString("name"));
                taskEntity.setDescription(resultSet.getString("description"));
                taskEntity.setCreateAt(resultSet.getObject("created_at", Instant.class));
                taskEntity.setIsDeleted(resultSet.getBoolean("is_deleted"));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных, во время создания таблицы!" + e.getMessage());
            System.exit(-1); //Скорее всего не нужен!?
        }
        return taskEntity;
    }

    @Override
    public TaskDto getTask(UUID taskId) {
        TaskDto taskDto = new TaskDto();

        try {
            Statement statement = connection.createStatement();                         //Встречается в приложении
            String sqlSelectById = "SELECT id FROM task_manager WHERE id = " + taskId;  //3 раза
            ResultSet resultSet = statement.executeQuery(sqlSelectById);                //Стоит вынести?

            taskDto.setId(resultSet.getObject("id", UUID.class)); // Не нужен while т.к. 1 объект верно? ОЧЕНЬ ТУПОЙ ВОПРОС
            taskDto.setName(resultSet.getString("name"));
            taskDto.setDescription(resultSet.getString("description"));
            taskDto.setCreatedAt(resultSet.getObject("created_at", Instant.class));
            taskDto.setIsDeleted(resultSet.getBoolean("is_deleted"));

        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных, во время поиска задачи по ID!" + e.getMessage());
            System.exit(-1);//Скорее всего не нужно закрывать программу!? если не получается выполнить 1 запрос? или как?
        }

        return taskDto;
    }

    @Override
    public List<TaskDto> getTasks() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<TaskEntity> taskEntityList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sqlSelect = "SELECT * FROM task_manager";
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                TaskEntity taskEntity = new TaskEntity();

                taskEntity.setId(resultSet.getObject("id", UUID.class));
                taskEntity.setName(resultSet.getString("name"));
                taskEntity.setDescription(resultSet.getString("description"));
                taskEntity.setCreateAt(resultSet.getObject("created_at", Instant.class));
                taskEntity.setIsDeleted(resultSet.getBoolean("is_deleted"));

                taskEntityList.add(taskEntity);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных, во время поиска задачи по ID!" + e.getMessage());
            System.exit(-1);//Скорее всего не нужно закрывать программу!? если не получается выполнить 1 запрос? или как?
        }
        for (TaskEntity task : taskEntityList) {
            taskDtoList.add(new TaskDto(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getCreateAt(),
                    task.getIsDeleted()));
        }

        return taskDtoList;
    }


    @Override
    @Transactional
    public void updateTask(UUID taskId, TaskDto taskDto) {
        try {
            Statement statement = connection.createStatement();

            if (taskDto.getName() != null) {
                String sqlUpdate = "UPDATE task_manager SET name = "
                        + taskDto.getName() + " WHERE id = " + taskId;
                statement.executeUpdate(sqlUpdate);
            }
            if (taskDto.getDescription() != null) {
                String sqlUpdate = "UPDATE task_manager SET description = "
                        + taskDto.getDescription() + " WHERE id = " + taskId;
                statement.executeUpdate(sqlUpdate);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных, во время обновления задачи по ID!" + e.getMessage());
            System.exit(-1);//Скорее всего не нужно закрывать программу!? если не получается выполнить 1 запрос? или как?
        }
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        try {
            Statement statement = connection.createStatement();

            String sqlDelete = "UPDATE task_manager SET is_deleted = true"
                    + " WHERE id = " + taskId;
            statement.executeUpdate(sqlDelete);

        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных, во время удаления задачи по ID!" + e.getMessage());
            System.exit(-1);//Скорее всего не нужно закрывать программу!? если не получается выполнить 1 запрос? или как?
        }
    }

}
