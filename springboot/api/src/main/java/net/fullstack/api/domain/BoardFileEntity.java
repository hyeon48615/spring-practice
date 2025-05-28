package net.fullstack.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"board"})
@Table(name="tbl_board_files")
public class BoardFileEntity implements Comparable<BoardFileEntity> {
    @Id
    private String uuid;
    private String fileName;
    private String fileType;
    private String fileSize;
    private int ord;
    private boolean imageFlag;

    @ManyToOne
    private BoardEntity board;

    @Override
    public int compareTo(BoardFileEntity o) {
        return this.ord = o.ord;
    }

    public void modifyBoard(BoardEntity board) {
        this.board = board;
    }
}
