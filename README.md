# 使用效果
![image](https://github.com/Larissa-x/WaveView/blob/master/app/src/main/res/drawable/wave_test.gif)

## 依赖
```
dependencies {
    implementation 'com.github.Larissa-x:WaveView:1.0.0'
}
```
## xml文件
```
 <com.example.waveview.WaveView
        android:id="@+id/wv"
        android:layout_centerInParent="true"
        android:layout_width="360dp"
        android:layout_height="360dp"/>
```

### 可用方法
#### start();       //动画开始执行
#### stop();        //动画停止执行
#### setWaveColor(int alpha, int red, int green ,int blue);    //ARGB颜色值，根据自己的需求填写
## Activity中

```
public class MainActivity extends AppCompatActivity {

    private WaveView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        wv = (WaveView) findViewById(R.id.wv);
        wv.start();
    }
}
```