/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class LibertyHelloController {

    @RequestMapping("/springbootweb")
    public String hello() {
        return "Hello from Spring Boot MVC running on Liberty!";
    }


    @RequestMapping("/jim")
    public String jim() {
        StringBuilder sb = new StringBuilder("JNDI >>> <p>");
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/sample");
            try (Connection c = ds.getConnection()) {
                sb.append(c.getMetaData().getDatabaseProductName());
            }
        }
        catch (Exception e) {
            sb.append(">>>>").append(e.getMessage());
        }
        return sb.toString();
    }

}