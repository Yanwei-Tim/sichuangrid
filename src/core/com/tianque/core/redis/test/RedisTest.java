package com.tianque.core.redis.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPoolConfig;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.core.redis.connection.RedisConnectionFactory;
import com.tianque.core.redis.core.RedisTemplate;
import com.tianque.core.redis.core.RedisTemplateImpl;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.property.OrganizationLevel;

/**
 * @ClassName: RedisTest
 * @Description: redis测试
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月8日 上午10:24:08
 */
public class RedisTest extends BaseTest {

	@Autowired
	private RedisTemplate redisTemplate;

	private static long userTime = 0;

	private static int threadIndex = 0;

	private static CacheNameSpaceEnum namespace = CacheNameSpaceEnum.PEOPLE_DEFAULTPAGE;

	public static void main(String[] args) {
		RedisTemplateImpl redisTemplateImpl = getRedisTemplate();
		userTime = 0;
		try {
			for (int i = 0; i < 5000; i++) {
				new Thread(new RedisTestThread(redisTemplateImpl)).start();
				//			new Thread(new RedisTestThread(getRedisTemplate())).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static RedisTemplateImpl getRedisTemplate() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1000);
		config.setMaxIdle(300);
		config.setMaxWaitMillis(30000);
		config.setTestOnBorrow(true);
		RedisTemplateImpl redisTemplateImpl = new RedisTemplateImpl();
		RedisConnectionFactory redisConnectionFactory = new RedisConnectionFactory();
		redisConnectionFactory.setAddress("127.0.0.1");
		redisConnectionFactory.setPort(6379);
		redisConnectionFactory.setTimeout(3000);
		redisConnectionFactory.setPoolConfig(config);
		//		redisConnectionFactory.setJedisPool(new JedisPool(config, "127.0.0.1",
		//				6379, 30000));
		redisConnectionFactory.init();
		//redisTemplateImpl.setConnectionFactory(redisConnectionFactory);
		return redisTemplateImpl;
	}

	public void mainTest(RedisTemplate redisTemplate) throws Exception {
		//		RedisTest redisTest = new RedisTest();
		//		redisTest.setRedisTemplate(redisTemplate);
		//		redisTest.testString();
		//		redisTest.testList();
		//		redisTest.checkList();
		//		redisTest.checkModeList();
		//		redisTest.mainThread();
		mainThread(redisTemplate);
	}

	public void mainThread(RedisTemplate redisTemplate) throws Exception {
		long l1 = System.currentTimeMillis();
		redisTemplate.set("keyId2", namespace, "");
		redisTemplate.get("keyId3", namespace);
		//				redisTemplate.addListFirst("sss", "ttttttttttttttttttt");
		redisTemplate.del("keyId5", namespace);
		userTime = (System.currentTimeMillis() - l1);
		System.out.println("耗时：" + userTime);
	}

	String moduleName = "redisPageTest";
	Long orgId = 63324L;

	@Test
	public void checkModeListThread() throws Exception {
		RedisDefaultPageUtils.clearList(moduleName, orgId);
		List<People> list = new ArrayList<People>();
		for (int i = 1; i <= 40; i++) {
			People people = new People();
			people.setId(new Long(i));
			people.setName("name-" + i);
			list.add(people);
		}
		RedisDefaultPageUtils.setPageInfo(1, 90, moduleName, orgId, list);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			People people = list.get(i);
			sb.append("i=" + i + "==").append(people.getName()).append("\n");
		}
		System.out.println("===" + sb.toString());
		for (int i = 0; i < 0; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						checkModeList(threadIndex++);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	//	@Test
	public void checkModeList(int update) throws Exception {
		People addPeople = new People();
		addPeople.setId(new Long(666 + update));
		addPeople.setName("wxh-666" + update);
		RedisDefaultPageUtils.addToList(moduleName, orgId,
				OrganizationLevel.TOWN, addPeople);

		People updatePeople = new People();
		updatePeople.setId(new Long(3));
		updatePeople.setName("wxh-3");

		People updatePeople2 = new People();
		updatePeople2.setId(new Long(6));
		updatePeople2.setName("wxl-6");

		RedisDefaultPageUtils.updateList(moduleName, orgId,
				OrganizationLevel.TOWN, updatePeople);
		RedisDefaultPageUtils.updateList(moduleName, orgId,
				OrganizationLevel.TOWN, updatePeople2);

		People delPeople = new People();
		delPeople.setId(new Long(9));
		RedisDefaultPageUtils.delFromList(moduleName, orgId,
				OrganizationLevel.TOWN, delPeople);

		List<People> list = (List<People>) RedisDefaultPageUtils.getPageInfo(
				moduleName, orgId, 2, 18, People.class);
		StringBuffer sb = new StringBuffer("list=");
		for (int i = 0; list != null && i < list.size(); i++) {
			sb.append(list.get(i).getName()).append("\t\t");
		}
		System.out.println(sb.toString());
	}

	@Test
	public void checkListThread() {
		for (int i = 0; i < 2; i++) {
			new Thread(new RedisTestListThread(getRedisTemplate())).start();
		}
	}

	@Test
	public void checkList() throws Exception {
		String key = "REDIS_CACHE_63324_cache_page_HouseholdStaff";
		String keyId = "REDIS_CACHE_63324_cache_page_HouseholdStaff_ID_CACHE";
		String ids = (String) redisTemplate.get(keyId, namespace);
		List<People> list = (List<People>) redisTemplate.getList(key,
				namespace, People.class);
		System.out.println("not null="
				+ (StringUtil.isStringAvaliable(ids) && list != null));
		String[] id = ids.split(",");
		System.out.println("size=" + list.size() + "   length=" + id.length);
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			long listId = list.get(i).getId();
			String cacheId = id[i + 1];
			//			PrintStream ps = System.out;
			if (!cacheId.equals(listId + "")) {
				//				ps = System.err;
				j++;
			}
			//			ps.println((i + 1) + "-->" + cacheId + "==" + listId + "=="
			//					+ (cacheId.equals(listId + "")));
		}
		if (j != 0)
			System.out.println("共 " + list.size() + "数据，异常数据  " + j + " 条");
	}

	@Test
	public void testList() throws Exception {
		redisTemplate.del("setList", namespace);
		long startTime = System.currentTimeMillis();
		List list = new ArrayList();
		for (int i = 1; i <= 36; i++) {
			list.add("abc" + i);
		}
		redisTemplate.createList("setList", namespace, list);
		System.out.println("耗时：" + (System.currentTimeMillis() - startTime));

		System.out.println("长度："
				+ redisTemplate.getListSize("setList", namespace));

		List<Object> returnList = (List<Object>) redisTemplate.getListForPage(
				"setList", namespace, 1, 10, People.class);
		for (Object obj : returnList) {
			System.out.print(obj + "\t");
		}
		System.out.println();
		System.out.println();
		System.out.println();
		//		redisTemplate.addListFirst("setList", "first");
		//		redisTemplate.addListLast("setList", "last");
		redisTemplate.removeList("setList", namespace, 0);

		returnList = (List<Object>) redisTemplate.getListForPage("setList",
				namespace, 1, 10, People.class);
		for (Object obj : returnList) {
			System.out.print(obj + "\t");
		}
	}

	@Test
	public void testString() throws Exception {
		long startTime = System.currentTimeMillis();
		redisTemplate.set("setkey", namespace, "wxl");
		String getValue = (String) redisTemplate.get("setkey", namespace);
		System.out.println("wxl=" + getValue);

		redisTemplate.setWhenNotExists("setkey1", namespace, "wxl213");
		getValue = (String) redisTemplate.get("setkey", namespace);
		System.out.println("wxl=" + getValue);

		redisTemplate.setWhenNotExists("setkey1", namespace, "wxl213");
		getValue = (String) redisTemplate.get("setkey1", namespace);
		System.out.println("wxl213=" + getValue);

		redisTemplate.set("setkeyOnTime", namespace, 1, "wxl22");
		getValue = (String) redisTemplate.get("setkeyOnTime", namespace);
		System.out.println("wxl22=" + getValue);
		//		try {
		//			Thread.sleep(2000L);
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}
		getValue = (String) redisTemplate.get("setkeyOnTime", namespace);
		System.out.println("null=" + getValue);

		getValue = (String) redisTemplate.get("setkey1", namespace);
		System.out.println("wxl213wxl213=" + getValue);
		redisTemplate.del("setkey1", namespace);
		getValue = (String) redisTemplate.get("setkey1", namespace);
		System.out.println("null=" + getValue);
		System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
