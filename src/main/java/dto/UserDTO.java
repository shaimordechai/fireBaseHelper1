package dto;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {
    private Long id;
    private String name;

    public UserDTO(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> res = new HashMap<>();
        res.put("id", this.id);
        res.put("name", this.name);
        return res;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
