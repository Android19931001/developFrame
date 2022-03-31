# developFrame
集成了作者 做安卓APP 比较好用的各种第三方和基类，接私活挺好用
继承方式：在根目录增加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
在grale中增加(1.1.5最新版本)：
dependencies {
	        implementation 'com.github.Android19931001:developFrame:1.1.5'
	}
