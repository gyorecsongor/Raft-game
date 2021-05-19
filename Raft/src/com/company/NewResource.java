package com.company;

import java.util.Random;
import static com.company.Datas.*;
import static com.company.GamePanel.*;
/**
 * Új anyag
 * Minden cselekvéskor megjelenik a pálya tetején egy új anyag,
 * ami lehet fa, levél, hulladék, hordó.(32-32-32-4 % esély)
 */
public class NewResource {
    public static void newResource(){
        Random random = new Random();
        int piece = random.nextInt(3);
        for (int i = 0; i < piece; i++){
            int number = random.nextInt(100);
            if (number < 32){
                items.addCollectables(random.nextInt(SCREEN_WIDTH / UNIT_SIZE)* UNIT_SIZE, 0, 'W');
            }else if (number < 63){
                items.addCollectables(random.nextInt(SCREEN_WIDTH / UNIT_SIZE)* UNIT_SIZE, 0, 'L');
            }else if (number < 95){
                items.addCollectables(random.nextInt(SCREEN_WIDTH / UNIT_SIZE)* UNIT_SIZE, 0, 'G');
            }else {
                items.addCollectables(random.nextInt(SCREEN_WIDTH / UNIT_SIZE)* UNIT_SIZE, 0, 'B');
            }
        }
    }
}
