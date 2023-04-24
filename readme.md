## 用户导航

### 一、创建项目和布局

1. 使用 `Empty Activity` 模板创建新项目。
2. 在`build.gradle` 文件中添加`implementation 'com.android.support:design:26.1.0'`，若Android Studio 建议的版本数字较大则使用Android Studio建议的版本
3. 若要使用应用栏和应用标题，需要在styles.xml 或 themes.xml 文件中添加如下代码以隐藏应用栏和标题。

```xml
<item name="windowActionBar">false</item>
<item name="windowNoTitle">true</item>
```

4. 打开布局文件 `activity_main.xml` 然后将布局改为相对布局。修改为如下所示

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:background="?attr/colorPrimary"
        android:minHeight="?attr/actionBarSize"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>


    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_above="@+id/pager"
        android:layout_alignParentTop="true"
        android:background="?attr/colorPrimary"
        android:minHeight="?attr/actionBarSize"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />

    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="fill_parent"
        android:layout_below="@id/toolbar" />




</RelativeLayout>
```

---

### 二、为每个fragment创建类和布局。

1. 单击 `Android > Project > work.icu007.tabexperiment`
2. 右键 `new > Fragment > Fragment(blank) `
3. 将其命名为 `TabFragment1`
4. `TabFragment1` 当中只留下 空的构造方法，以及 `onCreateView` 方法。
5. 重复 1-4 步 新增 `TabFragment2` 与 `TabFragment3`
6. `TabFragment`代码如下：

```java
public class TabFragment1 extends Fragment {



    public TabFragment1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }
}
```

---

### 三、编辑fragment布局文件

- 编辑每个Fragment的布局xml文件(`tab_fragment1`、`tab_fragment2` 和`tab_fragment3`)

1. `FrameLayout` 更改为 相对布局(`RelativeLayout`);
2. 将`TextView`文本更改为`“These are the top stories”`，并将 `layout_width` 和 `layout_height`更改为`wrap_content`。
3. 使用 `android:textAppearance="?android:attr/textAppearanceLarge"` 设置文本外观。

- 对每个片段布局xml 文件重复上述步骤。`TextView` 需根据情况而定。
- 检查每个`Fragment`例如`fragment_tab2.xml` 如下所示：

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.android.tabexperiment.TabFragment1">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="These are the top stories: "
        android:textAppearance="?android:attr/textAppearanceLarge"/>

</RelativeLayout>
```

---

4. 提取字符串

### 四、添加PagerAdapter

- adapter-layout 管理器模式可以让我们在一个活动中提供不同的屏幕内容。

  1. 使用适配器来填充整个屏幕内容用于展示在Activity当中；
  2. 使用布局管理器来根据选择的选项卡改变屏幕内容。
- 按照以下步骤来为app添加一个新的 `PagerAdapter` 类，此类继承自 `FragmentStatePagerAdapter` 并且定义了 标签数量(`mNumofTabs`)

  1. 在 `Android > Project` 面板中点击 `work.icu007.tabexperiment `
  2. 选择` File > New > Java Class`.
  3. 命名为`PagerAdapter`，继承自 `FragmentStatePagerAdapter` 导包时选择`android.support.v4.app.FragmentStatePagerAdapter`.。
  4. 在 `Android > Project` 面板中打开 `PagerAdapter` 会出现红色小灯泡，点击红色小灯泡，选择 Implement methods然后点击OK实现已经选择的 ` getItem()` 和`getCount()` 方法。

     `getItem()` 方法是在 `PagerAdapter` 对象需要获取与特定位置关联的 Fragment 时执行的。例如，在 ViewPager 中滑动到新的页面时，`ViewPager` 会调用适配器的 `getItem()` 方法，以便获取相应位置的 Fragment 并将其添加到视图中。

     当 `ViewPager` 需要显示一个新的页面时，它会调用 `PagerAdapter` 的 `getItem()` 方法，并传递要显示的页面位置（即参数 `i`）。然后，`getItem()` 方法返回一个与该位置对应的 Fragment 实例。这个 Fragment 将被添加到 ViewPager 的视图中，供用户查看和交互。

     `getItem()` 方法使用 `switch` 语句根据位置返回不同的 Fragment。如果位置无效，则返回 null。因此，如果使用了这个 PagerAdapter，当 ViewPager 准备显示新页面时，就会执行 `getItem()` 方法，并根据当前页面的位置返回对应的 Fragment 实例。

     总而言之，`getItem()` 方法是定义 PagerAdapter 的重要方法之一，它用于提供 ViewPager 中每个页面所需的 Fragment，并且在需要显示新页面时由 ViewPager 自动调用。
  5. 若还有类定义旁边小灯泡，则点击小灯泡后选择 `Create constructor matching super`.
  6. 添加整数类型成员变量 `mNumOfTabs` 并且更改构造函数以使用他。
  7. 代码如下所示：

  ```java
  public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
  }
  ```

  - 当键入上述代码时，Android Studio 会自动帮助导包。
  - 若 `FragmentManager` 代码被标红，点击红色灯泡 导入包 选择 FragmentManager (android.support.v4).

  8. 将新添加的 `getItem()` 方法更改为以下内容，该方法使用一个 `switch case` 代码块实现根据所选tab返回要显示的 `Fragment`

  ```java
  public Fragment getItem(int i) {
        switch (i){
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();
            default: return null;
        }
    }
  ```

  9. 更改新添加的 `getCount()` 方法来返回tabs数量

  ```java
    public int getCount() {
        return mNumOfTabs;
    }
  ```

---

### 五、扩充 Toolbar 和 TabLayout

- 因为使用的选项卡位于应用程序栏的下方，所以在此任务第一步中使用 `activity_main.xml` 布局中设置了应用程序栏和工具栏。接下来开始填充工具栏，并创建一个 TabLayout 实例来定位选项卡。。

  1. 打开 MainActivity 并且在 `onCreate()` 方法中添加如下代码来使用`setSupportActionBar()`填充`Toolbar`

  ```java
  // 通过view中的R.id.toolbar实例化一个 toolbar对象；
  android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
  // 将 toolbar设置为 ActionBar；
  setSupportActionBar(toolbar);
  ```

  2. 在 `onCreate()` 方法的末端根据布局中的 `tab_layout` 元素创建一个 tab layout的实例，并使用 `addTab ()`为每个 tab 设置文本:

  ```java
  // 通过view中的R.id.tab_layout 创建TabLayout的实例。
  TabLayout tabLayout = findViewById(R.id.tab_layout);

  // 为每个tabLayout设置文本
  tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
  tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
  tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
  // 设置选项卡为完全填充
  tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
  ```

---

### 六、使用PagerAdapter 管理screen views

1. 在之前任务添加的 `onCreate()` 方法的代码下面，添加如下代码以使用PagerAdapter管理Fragment中的屏幕视图。

```java
// 使用 PagerAdapter 管理fragments中的页面视图。
// 每个页面都由它自己的fragment表示。
final ViewPager viewPager = findViewById(R.id.pager);
final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
viewPager.setAdapter(adapter);
```

2. 在  `onCreate()` 方法的末尾，设置一个监听器(TabLayoutOnPageChangeListener)来监测是否单击了一个选项卡然后创建onTabSelected()方法来将ViewPager设置为相应的选项卡屏幕。

```java
// 设置点击侦听器
viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    // 选项卡被选择时的处理事件。
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});
```

3. 运行应用程序。点击每个选项卡可以看到每个“page”(屏幕)。能够左右滑动来访问不同的“page”。
