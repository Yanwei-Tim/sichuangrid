package com.tianque.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.ArrayUtils;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class BaseDomainTestHelper {

	/**
	 * 获得DOMAIN对象
	 * 
	 * @param 给对象设值时的内容
	 * @param domain
	 * @param org
	 * @param skip
	 *        需要跳过设值的属性
	 * @param special
	 *        特殊的属性
	 * @param specialArgs
	 *        特殊属性的值
	 */
	public static <T extends BaseDomain> T getDomain(String str, T domain, Organization org,
			String[] skip, String[] special, Object[] specialArgs) {
		return creatDomain(str, domain, org, skip, special, specialArgs);
	}

	/**
	 * 验证新增时，VO对象的创建人和创建日期不为空
	 * 
	 * @param actual
	 */
	// public static void compareCreatePropertyWhenAdd(BaseDomain actual) {
	// assertNotNull(actual.getCreateDate());
	// assertNotNull(actual.getCreateUser());
	// }

	/**
	 * 验证新增时，VO和DOMAIN对象的创建人和创建日期匹配
	 * 
	 * @param except
	 * @param actual
	 */
	// public static void compareCreatePropertyWhenAdd(BaseDomain domain,
	// BaseDomain vo) {
	// assertEquals(domain.getCreateDate(), vo.getCreateDate());
	// assertEquals(domain.getCreateUser(), vo.getCreateUser());
	// }

	/**
	 * 验证修改时，VO的修改日期和修改人不为空
	 * 
	 * @param domain
	 */
	public static void compareUpdatePropertyWhenUpdate(BaseDomain domain) {
		assertNotNull(domain.getUpdateDate());
		assertNotNull(domain.getUpdateUser());
	}

	/**
	 * 验证删除后，再查找该对象为空
	 * 
	 * @param vo
	 */
	public static <Vo extends BaseDomain> void compareObjectNullAfterDelete(Vo vo) {
		assertEquals(vo, null);
	}

	/**
	 * 验证列表页面数据
	 * 
	 * @param rightResult
	 *        正确的结果
	 * @param realResult
	 *        实际结果
	 */
	public static <Vo extends BaseDomain> void comparePageInfo(int[] rightResult, int[] realResult) {
		for (int i = 0; i < realResult.length; i++) {
			System.out.println("进行第" + (i + 1) + "组数据的比对");
			assertEquals(rightResult[i], realResult[i]);
		}
	}

	/**
	 * 验证PageInfo参数正确
	 */
	public static void compareArgs(int arg) {
		assertEquals(arg >= 1, true);
	}

	/**
	 * 验证修改时，VO和DOMAIN对象的创建人和创建日期匹配
	 * 
	 * @param domain
	 * @param vo
	 */
	public static void compareCreatePropertyWhenUpdate(BaseDomain domain, BaseDomain vo) {
		assertEquals(domain.getUpdateDate(), vo.getUpdateDate());
		assertEquals(domain.getUpdateUser(), vo.getUpdateUser());
	}

	/**
	 * 创建domain
	 * 
	 * @param 给对象设值时的内容
	 * @param domain
	 * @param org
	 * @param skip
	 *        需要跳过设值的属性
	 * @param special
	 *        特殊的属性
	 * @param specialArgs
	 *        特殊属性的值
	 */
	public static <T extends BaseDomain> T creatDomain(String str, T domain, Organization org,
			String[] skip, String[] special, Object[] specialArgs) {
		try {
			domain.setId(1l);
			setFieldValue(str, domain, org, skip, special, specialArgs);
			return domain;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * domain 和 vo 对比
	 * 
	 * @param domain
	 * @param vo
	 * @param skipComp
	 *        需要跳过比对的方法
	 */
	public static <T extends BaseDomain, Vo extends BaseDomain> void comperToReturnVo(T domain,
			Vo vo, String[] skipComp) {
		// DOMAIN类
		Class<?> objectDomain = domain.getClass();
		Method[] methodsDomain = objectDomain.getDeclaredMethods();
		Field[] fieldsDomain = objectDomain.getDeclaredFields();
		// VO类
		Class<?> objectVo = vo.getClass();
		Method[] methodsVo = objectVo.getDeclaredMethods();
		// VO的父类
		Class superClass = null;
		Method[] superMethods = null;
		if (objectVo.getGenericSuperclass() != null) {
			superClass = objectVo.getSuperclass();
			superMethods = superClass.getDeclaredMethods();
		}
		Method[] newMethods = (Method[]) ArrayUtils.addAll(methodsVo, superMethods);
		for (Field field : fieldsDomain) {
			try {
				String fieldGetName = parGetName(field.getName());
				if ("getSimplePinyin".equals(fieldGetName) || "getFullPinyin".equals(fieldGetName)
						|| "getCreateDate".equals(fieldGetName)
						|| "getCreateUser".equals(fieldGetName)) {
					continue;
				}
				if (!checkGetMet(methodsDomain, fieldGetName)) {
					System.out.println("Domain没有" + fieldGetName + "方法");
					continue;
				}
				if (!checkGetMet(newMethods, fieldGetName)) {
					System.out.println("Vo以及父类都没有" + fieldGetName + "方法");
					continue;
				}
				boolean isContinue = false;
				for (int i = 0; i < skipComp.length; i++) {
					if (skipComp[i].equals(fieldGetName)) {
						System.out.println("跳过" + fieldGetName + "方法");
						isContinue = true;
					}
				}
				if (isContinue) {
					continue;
				}
				Method fieldGetMetDomain = objectDomain.getMethod(fieldGetName);
				if (fieldGetMetDomain.invoke(domain) == null) {
					System.out.println(fieldGetMetDomain.getName() + "####--DOMAIN方法值为NULL");
				} else {
					if (fieldGetMetDomain.invoke(vo) == null) {
						System.out
								.println(fieldGetMetDomain.getName()
										+ "$$$$--VO方法值为NULL，DOMAIN方法值为："
										+ fieldGetMetDomain.invoke(domain));
					} else {
						System.out.print("进行" + fieldGetName + "方法的比对");
						if ("getOrg".equals(fieldGetName)) {
							Organization orgDomain = (Organization) fieldGetMetDomain
									.invoke(domain);
							Organization orgVo = (Organization) fieldGetMetDomain.invoke(vo);
							System.out.print("----比对ID:");
							assertEquals(orgDomain.getId(), orgVo.getId());
						} else if ("getTeamType".equals(fieldGetName)) {
							PropertyDict dictDomain = (PropertyDict) fieldGetMetDomain
									.invoke(domain);
							PropertyDict dictVo = (PropertyDict) fieldGetMetDomain.invoke(vo);
							System.out.println("----比对ID:");
							assertEquals(dictDomain.getId(), dictVo.getId());
						} else {
							assertEquals(fieldGetMetDomain.invoke(domain),
									fieldGetMetDomain.invoke(vo));
						}
						System.out.print("-----------------对比成功\n");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * set属性的值到Bean
	 * 
	 * @param 给对象设值时的内容
	 * @param domain
	 * @param org
	 * @param skip
	 *        需要跳过设值的属性
	 * @param special
	 *        特殊的属性
	 * @param specialArgs
	 *        特殊属性的值
	 */
	public static <T extends BaseDomain> void setFieldValue(String str, T domain, Organization org,
			String[] skip, String[] special, Object[] specialArgs) {
		Class<?> cls = domain.getClass();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		// 取出所有属性
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					System.out.println("DOMAIN没有此SET方法：\t" + fieldSetName);
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
				boolean isContinue = false;
				for (int i = 0; i < skip.length; i++) {
					if (fieldSetMet.getName().equals(skip[i])) {
						System.out.println("跳过设置DOMAIN的属性" + field.getName());
						isContinue = true;
					}
				}
				if (isContinue) {
					continue;
				}
				// 属性的类型 fieldType
				String fieldType = field.getType().getSimpleName();
				if ("String".equals(fieldType)) {
					fieldSetMet.invoke(domain, str + field.getName());
				} else if ("Date".equals(fieldType)) {
					Date temp = parseDate("2012-02-02 02:02:02");
					fieldSetMet.invoke(domain, temp);
				} else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
					Integer temp = Integer.parseInt("1");
					fieldSetMet.invoke(domain, temp);
				} else if ("Long".equalsIgnoreCase(fieldType) || "long".equals(fieldType)) {
					Long temp = Long.parseLong("2");
					fieldSetMet.invoke(domain, temp);
				} else if ("Double".equalsIgnoreCase(fieldType) || "double".equals(fieldType)) {
					Double temp = Double.parseDouble("3.0");
					fieldSetMet.invoke(domain, temp);
				} else if ("Boolean".equalsIgnoreCase(fieldType) || "boolean".equals(fieldType)) {
					Boolean temp = Boolean.parseBoolean("true");
					fieldSetMet.invoke(domain, temp);
				} else if ("Organization".equalsIgnoreCase(fieldType)) {
					fieldSetMet.invoke(domain, org);
				} else if ("PropertyDict".equalsIgnoreCase(fieldType)) {
					PropertyDict temp = new PropertyDict();
					temp.setId(1052l);
					fieldSetMet.invoke(domain, temp);
				} else {
					System.out.println("无法识别类型 \t" + fieldType);
					continue;
				}
				for (int i = 0; i < special.length; i++) {
					if (special[i].equals(fieldSetMet.getName())) {
						fieldSetMet.invoke(domain, specialArgs[i]);
						System.out
								.println("设置DOMAIN的属性" + field.getName() + "值为：" + specialArgs[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 取Bean的属性和值对应关系的MAP
	 * 
	 * @param bean
	 * @return Map
	 */
	// public static Map<String, String> getFieldValueMap(Object bean) {
	// Class<?> cls = bean.getClass();
	// Map<String, String> valueMap = new HashMap<String, String>();
	// // 取出bean里的所有方法
	// Method[] methods = cls.getDeclaredMethods();
	// Field[] fields = cls.getDeclaredFields();
	//
	// for (Field field : fields) {
	// try {
	// String fieldType = field.getType().getSimpleName();
	// String fieldGetName = parGetName(field.getName());
	// if (!checkGetMet(methods, fieldGetName)) {
	// continue;
	// }
	// Method fieldGetMet = cls
	// .getMethod(fieldGetName, new Class[] {});
	// Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
	// String result = null;
	// if ("Date".equals(fieldType)) {
	// result = fmtDate((Date) fieldVal);
	// } else {
	// if (null != fieldVal) {
	// result = String.valueOf(fieldVal);
	// }
	// }
	// valueMap.put(field.getName(), result);
	// } catch (Exception e) {
	// continue;
	// }
	// }
	// return valueMap;
	//
	// }

	/**
	 * 格式化string为Date
	 * 
	 * @param datestr
	 * @return date
	 */
	public static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {

				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期转化为String
	 * 
	 * @param date
	 * @return date string
	 */
	public static String fmtDate(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断是否存在某属性的 set方法
	 * 
	 * @param methods
	 * @param fieldSetMet
	 * @return boolean
	 */
	public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否存在某属性的 get方法
	 * 
	 * @param methods
	 * @param fieldGetMet
	 * @return boolean
	 */
	public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method met : methods) {
			if (fieldGetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 拼接某属性的 get方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
