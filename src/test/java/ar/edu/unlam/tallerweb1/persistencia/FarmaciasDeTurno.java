package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public class FarmaciasDeTurno extends SpringTest {

	@Test
	@Transactional
	@Rollback(true)
	public void testFarmaciasDeTurnoUnDeterminadoDia() {
		Farmacia miFarmacia1 = new Farmacia("nombreFarmacia1", "11111111");
		Farmacia miFarmacia2 = new Farmacia("nombreFarmacia2", "22222222");
		Farmacia miFarmacia3 = new Farmacia("nombreFarmacia3", "33333333");
		Farmacia miFarmacia4 = new Farmacia("nombreFarmacia4", "44444444");
		Farmacia miFarmacia5 = new Farmacia("nombreFarmacia5", "55555555");
		Farmacia miFarmacia6 = new Farmacia("nombreFarmacia6", "66666666");
		miFarmacia1.setDiaDeTurno("Lunes");
		miFarmacia2.setDiaDeTurno("Martes");
		miFarmacia3.setDiaDeTurno("Miercoles");
		miFarmacia4.setDiaDeTurno("Jueves");
		miFarmacia5.setDiaDeTurno("Martes");
		miFarmacia6.setDiaDeTurno("Martes");
		getSession().save(miFarmacia1);
		getSession().save(miFarmacia2);
		getSession().save(miFarmacia3);
		getSession().save(miFarmacia4);
		getSession().save(miFarmacia5);
		getSession().save(miFarmacia6);
		Integer esperado = 3;
		Integer actual = getSession().createCriteria(Farmacia.class)
								.add(Restrictions.eqOrIsNull("diaDeTurno", "Martes"))
								.list()
								.size();
		assertEquals(esperado, actual);
	}
}
