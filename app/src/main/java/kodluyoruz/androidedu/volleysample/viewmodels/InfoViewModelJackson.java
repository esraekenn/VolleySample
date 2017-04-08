package kodluyoruz.androidedu.volleysample.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Gökhan ÖZTÜRK
 * 9.04.2017
 * CodeProject.G@gmail.com
 */
public class InfoViewModelJackson {

    private String name;
    private int age;

    @JsonProperty("email")
    private String e_mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
