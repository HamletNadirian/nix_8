package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.UUID;

public final class DBHelperUtil {

	private DBHelperUtil() {
	}

	public static String generateId(ArrayListMy<? extends BaseEntity> items) {
		String id = UUID.randomUUID().toString();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId().equals(id)) {
				return generateId(items);
			}
		}
		return id;
	}
}