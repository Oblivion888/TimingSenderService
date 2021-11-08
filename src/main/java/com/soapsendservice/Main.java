package com.soapsendservice;



public class Main {
    public static void main(String[] args) {
     TimeTrackingSenderServiceImpl trackingSenderService =
                new TimeTrackingSenderServiceImplService()
                        .getPort(com.soapsendservice.TimeTrackingSenderServiceImpl.class);
        trackingSenderService.sendMessage(1109266611l,  "lollolol");
//        trackingSenderService.sendMessage(430627864l, "Sashok_V", "lol");
    }
}
