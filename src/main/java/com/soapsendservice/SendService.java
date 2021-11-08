package com.soapsendservice;


//1109266611l,  "lollolol"
public class SendService {
    public static void sendMessage(Long chatID, String text) {
     TimeTrackingSenderServiceImpl trackingSenderService =
                new TimeTrackingSenderServiceImplService()
                        .getPort(com.soapsendservice.TimeTrackingSenderServiceImpl.class);
        trackingSenderService.sendMessage(chatID,  text);
//        trackingSenderService.sendMessage(430627864l, "Sashok_V", "lol");
    }

    public static void main(String[] args) {
        SendService.sendMessage(1109266611l,  "lollolol");
    }

}
