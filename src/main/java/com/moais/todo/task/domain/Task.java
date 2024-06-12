package com.moais.todo.task.domain;

import com.moais.todo.common.BaseTimeEntity;
import com.moais.todo.common.error.CustomException;
import com.moais.todo.common.error.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx_member_id_created_at", columnList = "member_id, created_at"))
@Getter
public class Task extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Long memberId;

    public Task(String content, Long memberId) {
        this.content = content;
        this.status = TaskStatus.TODO;
        this.memberId = memberId;
    }

    public void changeStatusTo(TaskStatus toStatus) {
        if (!this.status.canChangeTo(toStatus)) {
            throw new CustomException(ErrorCode.BAD_REQUEST,
                    String.format("Not allowed to change status %s to %s.", this.status, toStatus));
        }

        this.status = toStatus;
    }
}
