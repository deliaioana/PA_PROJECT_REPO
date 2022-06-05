package com.server.apis;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@RestController
@RequestMapping("/api")
public class LoadController {

    @PostMapping("/load/{id}")
    public String load(@PathVariable Integer id) {
        String solutionJSON = "";

        try{
            Connection connection = Database.getConnection();


            CallableStatement stmt = connection.prepareCall("{call get_sol(?,?)}");
            stmt.setInt(1, id);
            stmt.registerOutParameter(2, Types.LONGNVARCHAR);

            stmt.execute();

            solutionJSON = stmt.getString(2);

            String message = "";

            if(solutionJSON != null) {
                message = "Successfully loaded!";
            }
            else {
                message = "Unexpected problem during loading!";
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
        return solutionJSON;
    }
}
