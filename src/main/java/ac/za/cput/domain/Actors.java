package ac.za.cput.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Aphish on 2016/04/22.
 */
@Entity
public class Actors implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String age;
    private String surname;
    private String height;

    public Actors(){}

    public Actors(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.surname=builder.surname;
        this.age=builder.age;
        this.height=builder.height;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public String getHeight() {
        return height;
    }

    public static class Builder{

        private Long id;
        private String name;
        private String age;
        private String surname;
        private String height;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder surname(String surname){
            this.surname=surname;
            return this;
        }

        public Builder age(String age){
            this.age=age;
            return this;
        }

        public Builder height(String height){
            this.height=height;
            return this;
        }

        public Builder copy(Actors actors){

            this.id=actors.id;
            this.name=actors.name;
            this.surname=actors.surname;
            this.age=actors.age;
            this.height=actors.height;
            return this;
        }

        public Actors build(){
            return new Actors(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actors actors = (Actors) o;

        return id.equals(actors.id);

    }

    @Override
    public int hashCode() {
        return id!=null ? id.hashCode():0;
    }
}
