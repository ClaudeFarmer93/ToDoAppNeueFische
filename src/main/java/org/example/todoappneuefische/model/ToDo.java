package org.example.todoappneuefische.model;

import lombok.With;
import org.example.todoappneuefische.enums.Status;

@With
public record ToDo(String id, String description, Status status) {
}
