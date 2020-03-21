package myspringboot.myspringbootweb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Family {
    @Id
    private String id;

    @Field("FirstName")
    private String firstName;

    @Field("LastName")
    private String lastName;

    @Field("Age")
    private String age;

    @Field("Timestamp")
    private Date timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(id);
        buf.append(" ").append(age).append(" ").append(timestamp);
        return buf.toString();
    }
}
