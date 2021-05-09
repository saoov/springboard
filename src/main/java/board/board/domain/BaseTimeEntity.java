package board.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

/*
* 공통으로 사용하는 기본 엔티티
* @EntityListeners : JPA Entity에 Persist, Remove, Update, Load에 대한 event 전과 후에 대한 콜백 메서드 제공
* @MappedSuperclass : 객체 입장에서 공통 mapping정보가 필요할 경우 사용(부모클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때)
*/
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    @UpdateTimestamp
    @Column
    protected LocalDateTime modifiedDate;

    @Column
    @ColumnDefault("'Y'")
    protected char userYn = 'Y';

    @Column
    @ColumnDefault("'N'")
    protected char delYn = 'N';

}