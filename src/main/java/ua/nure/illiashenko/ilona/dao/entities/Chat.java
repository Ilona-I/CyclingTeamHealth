package ua.nure.illiashenko.ilona.dao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Chat {

    private int id;
    private String type;
}
