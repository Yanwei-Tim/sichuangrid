package com.tianque.mongodb;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianque.mongodb.demo.Address;
import com.tianque.mongodb.demo.Hotel;
import com.tianque.mongodb.demo.HotelDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml",
		"classpath*:applicationContext-development.xml" })
public class MorphiaTest extends TestCase {
	@Autowired
	private HotelDAO<Hotel> hotelDAO;

	@BeforeClass
	public static void setup() throws ClassNotFoundException {
		Class.forName("com.tianque.dbRouterManager.InitDevelopmentRouterManager");
	}

	private List<Hotel> getHotelList(int num) {
		List<Hotel> list = new ArrayList<Hotel>();
		for (int i = 0; i < num; i++) {
			Hotel hotel = new Hotel();
			hotel.setName("编号为[" + i + "]的旅店");
			hotel.setStars(i % 10);
			Address address = new Address();
			address.setCountry("中国");
			address.setCity("北京");
			address.setStreet("上帝南路");
			address.setPostCode("10000" + (i % 10));
			hotel.setAddress(address);
			list.add(hotel);
		}
		return list;
	}

	@Test
	public void test_init() {
		List<Hotel> hotelList = getHotelList(100);
		for (Hotel hotel : hotelList) {
			// Key<Hotel> key=hotelDAO.save(hotel,WriteConcern.SAFE);
			Key<Hotel> key = hotelDAO.save(hotel);
			System.out.println("id为[" + key.getId() + "]的记录已被插入");
		}
	}

	@Test
	public void test_find() {
		List<Hotel> hotels = hotelDAO.find(Hotel.class);
		System.out.println(hotels.size());
		for (Hotel hotel : hotels) {
			System.out.println(hotel);
		}
	}
}
