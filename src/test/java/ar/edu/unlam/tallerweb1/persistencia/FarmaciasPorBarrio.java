package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public class FarmaciasPorBarrio extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
	public void testBuscarTodasLasFarmaciasDeUnBarrio() {
		Barrio miBarrio1 = new Barrio("Barrio Luna");
		Barrio miBarrio2 = new Barrio("Villa Club");
		Direccion miDireccion1 = new Direccion("Liszt", "1111", miBarrio1);
		Direccion miDireccion2 = new Direccion("Schubert", "2222", miBarrio1);
		Direccion miDireccion3 = new Direccion("Beethoven", "3333", miBarrio1);
		Direccion miDireccion4 = new Direccion("Pedro Diaz", "4444", miBarrio2);
		Farmacia miFarmacia1 = new Farmacia("nombreFarmacia1", "11111111");
		Farmacia miFarmacia2 = new Farmacia("nombreFarmacia2", "22222222");
		Farmacia miFarmacia3 = new Farmacia("nombreFarmacia3", "33333333");
		Farmacia miFarmacia4 = new Farmacia("nombreFarmacia4", "44444444");
		miFarmacia1.setDireccion(miDireccion1);
		miFarmacia2.setDireccion(miDireccion2);
		miFarmacia3.setDireccion(miDireccion3);
		miFarmacia4.setDireccion(miDireccion4);
		getSession().save(miBarrio1);
		getSession().save(miBarrio2);
		getSession().save(miDireccion1);
		getSession().save(miDireccion2);
		getSession().save(miDireccion3);
		getSession().save(miDireccion4);
		getSession().save(miFarmacia1);
		getSession().save(miFarmacia2);
		getSession().save(miFarmacia3);
		getSession().save(miFarmacia4);
		Integer esperado = 3;
		Integer  actual = getSession().createCriteria(Farmacia.class)
								.createAlias("direccion", "dir")
								.createAlias("dir.barrio", "bar")
								.add(Restrictions.eq("bar.nombre", "Barrio Luna"))
								.list()
								.size();
		assertEquals(esperado, actual);
	}
}
