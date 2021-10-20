package ua.com.alevel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private static void preview() {
        System.out.println();
        System.out.println("Для того,чтобы использовать обычный реверс (по словам) введите - 1");
        System.out.println("Для того,что бы использовать обычный реверс (реверс всей строки) введите - 2");
        System.out.println("Для того,что бы использовать реверс по уканной подстроке в строке введите - 3");
        System.out.println("Для того,что бы использовать реверсс указанного символа (индекса) введите - 4 ");
        System.out.println("Для выхода нажимите - 0");
        System.out.println();
    }

  public static void run() {
      String choice;
      String src;
      String reverseString;
      String dest;
      int firstIndex;
      int lastIndex;
      preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((choice=reader.readLine()) !=null){
                switch (choice){
                    case "1":{
                        System.out.println("Введите строку:\n->");
                         src=reader.readLine();;
                        reverseString = ReverseStringUtil.reverse(src,true);
                        System.out.println("Входная стрка: "+src);
                        System.out.println("Выходная строка: "+reverseString);
                    }break;
                    case "2":{
                        System.out.println("Введите строку:\n->");
                         src=reader.readLine();;
                         reverseString = ReverseStringUtil.reverse(src,false);
                        System.out.println("Входная стрка: "+src);
                        System.out.println("Выходная строка: "+reverseString);
                    }break;
                    case "3":{
                        System.out.println("Введите строку:\n->");
                         src=reader.readLine();
                        System.out.println("Введите подстроку:\n->");
                        dest=reader.readLine();
                         reverseString = ReverseStringUtil.reverse(src,dest);
                        System.out.println("Входная стрка: "+src);
                        System.out.println("Выходная строка: "+reverseString);
                    }break;
                    case "4":{
                        System.out.println("Введите строку:\n->");
                        src=reader.readLine();
                        System.out.println("Введите с какого индекса:\n->");
                        firstIndex= Integer.parseInt(reader.readLine());
                        System.out.println("Введите до какого индекса:\n->");
                        lastIndex= Integer.parseInt(reader.readLine());
                        reverseString = ReverseStringUtil.reverse(src,firstIndex,lastIndex);
                        System.out.println("Входная стрка: "+src);
                        System.out.println("Выходная строка: "+reverseString);

                    }break;
                    case "0":{
                        System.exit(0);
                    }break;
                    default:
                        System.out.println("Cделайте правильный выбор");
                        break;

                }
                preview();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
