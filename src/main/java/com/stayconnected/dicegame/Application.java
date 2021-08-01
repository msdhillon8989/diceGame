package com.stayconnected.dicegame;


import com.stayconnected.dicegame.service.GameService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setLogStartupInfo(false);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(args==null || args.length<2){
            System.out.println("Please provide command line argument N and M");
            System.out.println("eg: java -jar dice-game 3 45");
        }else{
            try{
                GameService gameService = new GameService(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
                gameService.startGame();
            }catch (NumberFormatException e){
                System.out.println("Invalid argument: Kindly provide number only");
            }

        }

    }
}