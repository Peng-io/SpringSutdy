package demo;


import lombok.Data;

import java.util.*;

@Data
public class student {
    private String name;
    private User user;
    private String[] books;
    private List<String> course;
    private Map<String, String> card;
    private Set<String> games;
    private String wife;
    private Properties info;
}
