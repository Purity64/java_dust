package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import util.btn.RoundeBtn;

public class Config {
   public static int activate_btn = -1;
   public static boolean btn_togat_rain_make = false;

   public static Map<Integer , RoundeBtn> main_btn = new HashMap<>();
   public static Map<Integer , RoundeBtn> CopyMain_btn = new HashMap<>();

   public static ArrayList< Map<Integer, RoundeBtn>> history_btn = new ArrayList<>();
   public static ArrayList< Set<Integer> >  history_hover = new ArrayList<>();

   public static boolean is_show_history = false;
   public static int show_btn_key = -2;
   
   
}