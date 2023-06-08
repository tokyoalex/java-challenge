package jp.co.axa.apidemo;

import jp.co.axa.apidemo.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiDemoApplicationTests {

 	@Autowired
    private MockMvc mvc;

	@Test
	@Order(1)
	public void getEmployees()
			throws Exception {
		this.mvc.perform(get("/api/v1/employees")).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	@Order(2)
	public void postEmployees() throws Exception {

		Employee e1 = new Employee("david", 30000, "kitchen");
		Gson gson = new Gson();
		String json = gson.toJson(e1);

		mvc.perform( MockMvcRequestBuilders
						.post("/api/v1/employees")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	@Order(3)
	public void getEmployeeById() throws Exception {
		Long empId = 1L;

		mvc.perform( MockMvcRequestBuilders
						.get("/api/v1/employees/{employeeId}", empId))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alex"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(55000))
				.andExpect(MockMvcResultMatchers.jsonPath("$.department").value("dev"));
	}

	@Test
	@Order(4)
	public void getEmployeeByIdFail() throws Exception {
		Long empId = 123456L;

		mvc.perform( MockMvcRequestBuilders
						.get("/api/v1/employees/{employeeId}", empId))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(5)
	public void deleteEmployee() throws Exception {
		Long empId = 3L;
		mvc.perform( MockMvcRequestBuilders.delete("/api/v1/employees/{employeeId}", empId) )
				.andExpect(status().isAccepted());
	}

	@Test
	@Order(6)
	public void deleteEmployeeFail() throws Exception {
		Long empId = 123456L;
		mvc.perform( MockMvcRequestBuilders.delete("/api/v1/employees/{employeeId}", empId) )
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(7)
	public void putEmployee() throws Exception {
		Long empId = 2L;
		Employee e1 = new Employee("Neil", 1000000, "mobile");
		Gson gson = new Gson();
		String json = gson.toJson(e1);

		mvc.perform( MockMvcRequestBuilders
						.put("/api/v1/employees/{employeeId}", empId)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Neil"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(1000000))
				.andExpect(MockMvcResultMatchers.jsonPath("$.department").value("mobile"));
	}

	@Test
	@Order(8)
	public void putEmployeeFail() throws Exception {
		Long empId = 123456L;
		Employee e1 = new Employee("aaaaa", 66666, "mxmxmxm");
		Gson gson = new Gson();
		String json = gson.toJson(e1);

		mvc.perform( MockMvcRequestBuilders
						.put("/api/v1/employees/{employeeId}", empId)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
