package com.server.apis;

import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@RestController
@RequestMapping("/api")
public class AddController {

    @PostMapping("/add/{solution}")
    public String add(@PathVariable String solution) {

        try{
            Connection connection = Database.getConnection();

            int solutionID = -1;
            CallableStatement stmt = connection.prepareCall("{call add_sol(?,?)}");
            stmt.setString(1, solution);
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.execute();

            solutionID = stmt.getInt(2);

            String message = "";

            if(solutionID != 0) {
                message = "Successfully saved!";
            }
            else {
                message = "Unexpected problem during saving!";
            }

            System.out.println(message);

            connection.commit();
            stmt.close();
            //connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
