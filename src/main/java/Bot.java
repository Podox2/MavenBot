import model.WeatherApi;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

//heroku ps:scale worker=1
public class Bot extends TelegramLongPollingBot {
    final static String BOT_TOKEN = "1083596615:AAHZ9OJYuqrYSkQ_hO5-ERvHgmL_udcbH0Q";
    final static String BOT_USER_NAME = "MyTestVebinarPodoBot";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
            System.out.println("nice");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            System.out.println("fok");
        }
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendStartMsg(message, "Enter the city to get the weather");
                    break;

                case "Wiseau":
                    sendWiseauMsg(message, "Oh, hi Mark!");
                    break;
                case "/push_this":
                    sendWiseauMsg(message, "Anyway, how is your sex life?");
                    break;
                case "/then_this":
                    sendWiseauMsg(message, "You are tearing me apart Lisa!");
                    break;

                case "Weather":
                    sendWeatherMsg(message, "Enter the city to get the weather");
                    break;
                /*case "Vinnytsia":
                    sendWeatherMsg(message, WeatherApi.getWeather(message.getText()));
                    break;
                case "Kyiv":
                    sendWeatherMsg(message, WeatherApi.getWeather(message.getText()));
                    break;*/
                case "/help":
                    sendStartMsg(message, "Dude, just enter the city name to get the weather.\n" +
                            "Or use next commands:\n" +
                            "/push_this\n" +
                            "/then_this");
                    break;
                default:
                    sendWeatherMsg(message, WeatherApi.getWeather(message.getText()/*, weather*/));

            }
        }
    }


    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param message обьект с сообщением
     * @param answer  Строка, которую необходимот отправить в качестве сообщения.
     */
    public void sendStartMsg(Message message, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer);
        try {
            setStartButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendWeatherMsg(Message message, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer);
        try {
            setWeatherButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendWiseauMsg(Message message, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(answer);
        try {
            setWiseauButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setStartButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow firstKeyBoardRow = new KeyboardRow();
        firstKeyBoardRow.add(new KeyboardButton("Wiseau"));
        firstKeyBoardRow.add(new KeyboardButton("Weather"));

        keyboardRowList.add(firstKeyBoardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//
    }

    public void setWiseauButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow firstKeyBoardRow = new KeyboardRow();
        firstKeyBoardRow.add(new KeyboardButton("/push_this"));
        firstKeyBoardRow.add(new KeyboardButton("/then_this"));

        keyboardRowList.add(firstKeyBoardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//
    }

    public void setWeatherButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow firstKeyBoardRow = new KeyboardRow();
        firstKeyBoardRow.add(new KeyboardButton("Kyiv"));
        firstKeyBoardRow.add(new KeyboardButton("Vinnytsia"));


        KeyboardRow secondKeyBoardRow = new KeyboardRow();
        secondKeyBoardRow.add(new KeyboardButton("Odesa"));
        secondKeyBoardRow.add(new KeyboardButton("Lviv"));

        keyboardRowList.add(firstKeyBoardRow);
        keyboardRowList.add(secondKeyBoardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
