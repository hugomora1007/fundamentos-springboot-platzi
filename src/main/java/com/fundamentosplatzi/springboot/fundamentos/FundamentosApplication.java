package com.fundamentosplatzi.springboot.fundamentos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
			MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
			UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
	
	private void saveWithErrorTransactional() {
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		//User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now()); // da error
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());
		
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		
		try {
			this.userService.saveTransactional(users);
			
		} catch (Exception e) {
			LOGGER.error("Esta es una exepcion dentro del metodo transactional " + e);
		}
		
		this.userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional: " + user));
		
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Uusario con el metodo findByUserEmail " + this.userRepository.findByUserEmail("julie@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		LOGGER.info("--------------------------");
		this.userRepository.findAndSort("Ju", Sort.by("id").descending()).stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		LOGGER.info("--------------------------");
		this.userRepository.findByName("Anna").stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("--------------------------");
		LOGGER.info("Usuario con query method findByEmailAndName: "
				+ this.userRepository.findByEmailAndName("richard@domain.com", "Richard")
						.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		LOGGER.info("--------------------------");
		this.userRepository.findByNameLike("%a%").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike: " + user));

		LOGGER.info("--------------------------");
		this.userRepository.findByNameOrEmail(null, "peter@domain.com").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail mail: " + user));

		LOGGER.info("--------------------------");
		this.userRepository.findByNameOrEmail("Roxanna", null).stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail name: " + user));

		LOGGER.info("--------------------------");
		this.userRepository.findByBirthDateBetween(LocalDate.of(2003, 1, 1), LocalDate.of(2003, 12, 31)).stream()
				.forEach(user -> LOGGER.info("Usuario findByBirthDateBetween name: " + user));

		LOGGER.info("--------------------------");
		this.userRepository.findByNameLikeOrderByIdDesc("%a%").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc name: " + user));
		
		LOGGER.info("--------------------------");
		this.userRepository.findByNameContainingOrderByIdDesc("na").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameContainingOrderByIdDesc name: " + user));
		
		LOGGER.info("EL usuario a partir del named parameter es: " + this.userRepository
				.getAllByBirthDateAndEmail(LocalDate.of(2005, 10, 13), "richard@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	private void saveUsersInDataBase() {
		User user1 = new User("John", "john@domain.com", LocalDate.of(2000, 5, 10));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2005, 6, 11));
		User user3 = new User("Juan", "juan@domain.com", LocalDate.of(2003, 6, 6));
		User user4 = new User("Anna", "anna@domain.com", LocalDate.of(2003, 12, 7));
		User user5 = new User("Paul", "paul@domain.com", LocalDate.of(2005, 6, 17));
		User user6 = new User("Richard", "richard@domain.com", LocalDate.of(2005, 10, 13));
		User user7 = new User("Roxanna", "roxanna@domain.com", LocalDate.of(2005, 5, 12));
		User user8 = new User("Paulina", "paulina@domain.com", LocalDate.of(2005, 3, 4));
		User user9 = new User("Andre", "andre@domain.com", LocalDate.of(2005, 2, 17));
		User user10 = new User("Peter", "peter@domain.com", LocalDate.of(2005, 10, 25));
		User user11 = new User("Sophy", "sophy@domain.com", LocalDate.of(2005, 11, 29));
		User user12 = new User("Carla", "carla@domain.com", LocalDate.of(2005, 6, 14));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11,
				user12);
		list.stream().forEach(this.userRepository::save);
	}

	private void ejemplosAnteriores() {
		this.componentDependency.saludar();

		this.myBean.print();

		this.myBeanWithDependency.printWithDependency();

		System.out.println(this.myBeanWithProperties.function());

		System.out.println(this.userPojo.getMail() + " - " + this.userPojo.getPassword());

		try {
			int value = 10 / 0;
			LOGGER.debug("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por 0: " + e.getMessage());
		}
	}

}
