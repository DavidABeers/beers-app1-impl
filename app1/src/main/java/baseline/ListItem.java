/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

public class ListItem {
    private String name;
    private String due;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public String getDue() {
        return due;
    }

    public String getDescription() {
        return description;
    }
}
