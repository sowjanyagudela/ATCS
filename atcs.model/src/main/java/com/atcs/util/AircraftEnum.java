package com.atcs.util;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class AircraftEnum {

	public enum AircraftType {

		EMERGENCY(1), VIP(2), PASSENGER(3), CARGO(4);

		private final int value;

		private AircraftType(int value) {
			this.value = value;
		}

		private static Map map = new HashMap<>();

		static {
			for (AircraftType aircraftType : AircraftType.values()) {
				map.put(aircraftType.value, aircraftType);
			}
		}

		public static AircraftType valueOf(int status) {
			return (AircraftType) map.get(status);
		}

		public int getValue() {
			return value;
		}

		@Converter(autoApply = true)
		public static class AircraftTypeConverter implements AttributeConverter<AircraftType, Integer> {

			@Override
			public Integer convertToDatabaseColumn(AircraftType aircraftType) {
				if (null == aircraftType)
					return null;
				return aircraftType.getValue();
			}

			@Override
			public AircraftType convertToEntityAttribute(Integer dbData) {
				if (null == dbData)
					return null;
				return AircraftType.valueOf(dbData);
			}
		}
	}

	public enum AircraftSize {

		LARGE(1), SMALL(2);

		private final int value;

		private AircraftSize(int value) {
			this.value = value;
		}

		private static Map map = new HashMap<>();

		static {
			for (AircraftSize aircraftSize : AircraftSize.values()) {
				map.put(aircraftSize.value, aircraftSize);
			}
		}

		public static AircraftSize valueOf(int status) {
			return (AircraftSize) map.get(status);
		}

		public int getValue() {
			return value;
		}
		
		@Converter(autoApply = true)
		public static class AircraftSizeConverter implements AttributeConverter<AircraftSize, Integer> {

			@Override
			public Integer convertToDatabaseColumn(AircraftSize aircraftSize) {
				if (null == aircraftSize)
					return null;
				return aircraftSize.getValue();
			}

			@Override
			public AircraftSize convertToEntityAttribute(Integer dbData) {
				if (null == dbData)
					return null;
				return AircraftSize.valueOf(dbData);
			}
		}
	}

}
