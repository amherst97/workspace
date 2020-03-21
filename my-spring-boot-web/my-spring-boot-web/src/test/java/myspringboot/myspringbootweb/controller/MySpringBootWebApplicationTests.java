package myspringboot.myspringbootweb.controller;

import myspringboot.myspringbootweb.model.Family;
import myspringboot.myspringbootweb.service.FamilyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.easymock.EasyMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.easymock.EasyMock.*;


@RunWith(EasyMockRunner.class)
class MySpringBootWebApplicationTests {
	private Controller controller;
	private FamilyRepository familyRepository;

	@Test
	void testMemebers() {
		controller = new Controller();
		familyRepository = createMock(FamilyRepository.class);
		expect(familyRepository.findAll()).andReturn(Collections.emptyList());
		ReflectionTestUtils.setField(controller, "familyRepository", familyRepository);
		replay(familyRepository);
		assertTrue(controller.members().isEmpty());
	}

}
