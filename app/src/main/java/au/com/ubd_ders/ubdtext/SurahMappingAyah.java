package au.com.ubd_ders.ubdtext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boa on 10/17/17.
 */

public class SurahMappingAyah {

    private static Map<Integer, Integer> mapper = new HashMap<Integer, Integer>();

    public static void populateMapper() {
        mapper.put(1, 7);
        mapper.put(2, 286);
        mapper.put(3, 200);
        mapper.put(4,176);
        mapper.put(5, 120);
        mapper.put(6,165);
        mapper.put(7, 206);
        mapper.put(8, 75);
        mapper.put(9, 129);
        mapper.put(10, 109);
        mapper.put(11, 123);
        mapper.put(12, 111);
        mapper.put(13, 43);
        mapper.put(14, 52);
        mapper.put(15, 99);
        mapper.put(16, 128);
        mapper.put(17, 111);
        mapper.put(18, 110);
        mapper.put(19, 98);
        mapper.put(20, 135);
        mapper.put(21, 112);
        mapper.put(22, 78);
        mapper.put(23, 118);
        mapper.put(24, 64);
        mapper.put(25, 77);
        mapper.put(26, 227);
        mapper.put(27, 93);
        mapper.put(28, 88);
        mapper.put(29, 69);
        mapper.put(30, 60);
        mapper.put(31, 34);
        mapper.put(32, 30);
        mapper.put(33, 73);
        mapper.put(34, 54);
        mapper.put(35, 45);
        mapper.put(36, 83);
        mapper.put(37, 182);
        mapper.put(38, 88);
        mapper.put(39, 75);
        mapper.put(40, 85);
        mapper.put(41, 54);
        mapper.put(42, 53);
        mapper.put(43, 89);
        mapper.put(44, 59);
        mapper.put(45, 37);
        mapper.put(46, 35);
        mapper.put(47, 38);
        mapper.put(48, 28);
        mapper.put(49, 18);
        mapper.put(50, 45);
        mapper.put(51, 60);
        mapper.put(52, 49);
        mapper.put(53, 62);
        mapper.put(54, 55);
        mapper.put(55, 78);
        mapper.put(56, 96);
        mapper.put(57, 29);
        mapper.put(58, 22);
        mapper.put(59, 24);
        mapper.put(60, 13);
        mapper.put(61, 14);
        mapper.put(62, 11);
        mapper.put(63, 11);
        mapper.put(64, 18);
        mapper.put(65, 12);
        mapper.put(66, 12);
        mapper.put(67, 30);
        mapper.put(68, 52);
        mapper.put(69,30);
        mapper.put(70, 44);
        mapper.put(71, 28);
        mapper.put(72, 28);
        mapper.put(73, 20);
        mapper.put(74, 56);
        mapper.put(75, 40);
        mapper.put(76, 31);
        mapper.put(77, 50);
        mapper.put(78, 40);
        mapper.put(79, 46);
        mapper.put(80, 42); mapper.put(81, 29); mapper.put(82, 19); mapper.put(83, 36);mapper.put(84, 25);mapper.put(85, 22);mapper.put(86, 17)
        ;mapper.put(87, 19);mapper.put(88, 26);mapper.put(89, 30);
        mapper.put(90, 20); mapper.put(91, 15); mapper.put(92, 21); mapper.put(93, 11); mapper.put(94, 8);mapper.put(95, 8);
        mapper.put(96, 19); mapper.put(97, 5); mapper.put(98, 8); mapper.put(99,8);
        mapper.put(100, 11); mapper.put(101, 11); mapper.put(102, 8); mapper.put(103, 3);mapper.put(104, 9); mapper.put(105, 5);
        mapper.put(106, 4); mapper.put(107, 7);mapper.put(108, 3); mapper.put(109, 6);
        mapper.put(110, 3); mapper.put(111, 5); mapper.put(112, 4); mapper.put(113, 5); mapper.put(114, 6);

    }

    public static int getMapperValue(int i) {
        return mapper.get(i);
    }
}
