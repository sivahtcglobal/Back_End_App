package com.htc.resources.request;

/**
 * Created by giris on 27/1/20.
 */
public class SkillRequest {

    private String skillName;
    private int experience;

    public SkillRequest() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
