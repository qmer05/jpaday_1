package app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String username;
    private  String password;
    private  String email;

}