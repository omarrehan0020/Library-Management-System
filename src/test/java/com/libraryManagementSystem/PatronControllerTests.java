package com.libraryManagementSystem;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.libraryManagementSystem.controllers.PatronController;
import com.libraryManagementSystem.model.Patron;
import com.libraryManagementSystem.services.PatronService;

@SpringBootTest
class PatronControllerTests {
	
	private MockMvc mockMvc;

	@Mock
	private PatronService patronService;

	@InjectMocks
	private PatronController patronController;

	@BeforeEach
	public void setUp() {
	    MockitoAnnotations.openMocks(this);
	    mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
	}

	@Test
	public void testGetAllPatrons() throws Exception {
	    Patron patron1 = new Patron();
	    patron1.setId(1L);
	    patron1.setName("Patron 1");

	    Patron patron2 = new Patron();
	    patron2.setId(2L);
	    patron2.setName("Patron 2");

	    List<Patron> patrons = Arrays.asList(patron1, patron2);

	    when(patronService.getAllPatrons()).thenReturn(patrons);

	    ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertEquals(2, response.getBody().size());
	    verify(patronService, times(1)).getAllPatrons();
	}

	@Test
	public void testGetPatronById() throws Exception {
	    Long id = 1L;
	    Patron patron = new Patron();
	    patron.setId(id);
	    patron.setName("Patron Name");

	    when(patronService.getPatronById(id)).thenReturn(patron);

	    ResponseEntity<Patron> response = patronController.getPatronById(id);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertEquals(id, response.getBody().getId());
	    verify(patronService, times(1)).getPatronById(id);
	}

	@Test
	public void testAddPatron() throws Exception {
	    Patron patronToAdd = new Patron();
	    patronToAdd.setName("Test Patron");

	    when(patronService.addPatron(patronToAdd)).thenReturn(patronToAdd);

	    ResponseEntity<Patron> response = patronController.addPatron(patronToAdd);

	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    assertNotNull(response.getBody());
	    verify(patronService, times(1)).addPatron(patronToAdd);
	}

	@Test
	public void testDeletePatron() throws Exception {
	    Long patronId = 1L;

	    mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/{id}", patronId))
	            .andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void testUpdatePatron() throws Exception {
	    Long patronId = 1L;
	    Patron updatedPatron = new Patron();
	    updatedPatron.setId(patronId);
	    updatedPatron.setName("Updated Name");
	    updatedPatron.setEmail("updated_email@example.com");
	    updatedPatron.setPhoneNumber("1234567890");

	    // Stub the behavior of the updatePatron method in the patronService mock
	    when(patronService.updatePatron(patronId, updatedPatron)).thenReturn(updatedPatron);

	    ResponseEntity<Patron> response = patronController.updatePatron(patronId , updatedPatron);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertEquals(patronId, response.getBody().getId());

	    verify(patronService, times(1)).updatePatron(patronId, updatedPatron);
	}
}
