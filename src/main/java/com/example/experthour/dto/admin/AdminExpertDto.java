package com.example.experthour.dto.admin;

public class AdminExpertDto {

    private Long id;
    private String name;
    private boolean approved;

    public AdminExpertDto(Long id, String name, boolean approved) {
        this.id = id;
        this.name = name;
        this.approved = approved;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isApproved() { return approved; }
}


