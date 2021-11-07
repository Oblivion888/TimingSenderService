package com.sendservice;



public class Main {
    public static void main(String[] args) {
     TimeTrackingSenderServiceImpl trackingSenderService =
                new TimeTrackingSenderServiceImplService()
                        .getPort(com.sendservice.TimeTrackingSenderServiceImpl.class);
        trackingSenderService.sendMessage(336574141l, "lol");
//        trackingSenderService.sendMessage(430627864l, "Sashok_V", "lol");
    }
}
