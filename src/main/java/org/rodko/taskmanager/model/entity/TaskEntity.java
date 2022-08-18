package org.rodko.taskmanager.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "task_manager", name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskEntity {

    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "created_at")
    private Instant createAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
