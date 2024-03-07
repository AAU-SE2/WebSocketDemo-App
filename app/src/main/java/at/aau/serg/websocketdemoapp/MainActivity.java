package at.aau.serg.websocketdemoapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {

    private final String WEBSOCKET_URI = "ws://localhost:8080/websocket-example-handler";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(v -> connectToWebSocketServer());
    }

    private void connectToWebSocketServer(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(WEBSOCKET_URI)
                .build();

        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                // WebSocket connection opened
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                // Received message
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                // WebSocket connection closed
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                // WebSocket connection failure
            }
        });

        webSocket.send("TEST message");

    }
}