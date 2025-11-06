package org.example.todoappneuefische.dto;

import lombok.With;
import org.example.todoappneuefische.enums.Status;
@With
public record ToDoDtoInput( String description, Status status) {


}
