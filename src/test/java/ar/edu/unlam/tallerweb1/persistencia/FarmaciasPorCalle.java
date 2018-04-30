package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public class FarmaciasPorCalle extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
	public void testBuscarTodasLasFarmaciasDeUnaCalle() {
		Direccion miDireccion1 = new Direccion();
		Direccion miDireccion2 = new Direccion();
		Direccion miDireccion3 = new Direccion();
		Direccion miDireccion4 = new Direccion();
		miDireccion1.setCalle("Liszt");
		miDireccion2.setCalle("Liszt");
		miDireccion3.setCalle("Liszt");
		miDireccion4.setCalle("Pedro Diaz");
		Farmacia miFarmacia1 = new Farmacia("nombreFarmacia1", "11111111");
		Farmacia miFarmacia2 = new Farmacia("nombreFarmacia2", "22222222");
		Farmacia miFarmacia3 = new Farmacia("nombreFarmacia3", "33333333");
		Farmacia miFarmacia4 = new Farmacia("nombreFarmacia4", "44444444");
		miFarmacia1.setDireccion(miDireccion1);
		miFarmacia2.setDireccion(miDireccion2);
		miFarmacia3.setDireccion(miDireccion3);
		miFarmacia4.setDireccion(miDireccion4);
		getSession().save(miDireccion1);
		getSession().save(miDireccion2);
		getSession().save(miDireccion3);
		getSession().save(miDireccion4);
		getSession().save(miFarmacia1);
		getSession().save(miFarmacia2);
		getSession().save(miFarmacia3);
		getSession().save(miFarmacia4);
		Integer esperado = 3;
		Integer actual = getSession().createCriteria(Farmacia.class)
							.createAlias("direccion", "dir")
							.add(Restrictions.eq("dir.calle", "Liszt"))
							.list()
							.size();
		assertEquals(esperado,actual);
	}
	
}
