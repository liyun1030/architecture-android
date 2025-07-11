#include <iostream>
#include <iomanip>
#include <ctime>
#include <chrono>
#include <thread>
#include <string>

#ifdef _WIN32
#include <windows.h>  // Windows 系统
#else
#include <unistd.h>   // POSIX 系统 (Linux/Mac)
#define Sleep(ms) usleep((ms) * 1000)  // 跨平台 Sleep 实现
#endif
using namespace std;
// 跨平台时间转换工具类
class TimeUtils {
public:
	static void localtime(const time_t* t, struct tm* result) {
#ifdef _WIN32
		if (localtime_s(result, t) != 0) {
			throw std::runtime_error("localtime_s failed");
		}
#else
		localtime_r(t, result);  // POSIX 系统保证成功
#endif
	}
};
class ClockTest {
	public:
		void runTime() {
			while (true) {
				tick();
				show();
			}
	    }
		ClockTest() {
			updateTimeFromSystem();
		}
	private:
		int _hour;
		int _minute;
		int _second;
		void updateTimeFromSystem() {
			/*auto now = std::chrono::system_clock::now();
			time_t t = std::chrono::system_clock::to_time_t(now);

			#ifdef _WIN32
						struct tm local;
						localtime_s(&local, &t);
			#else
						struct tm local {};
						localtime_r(&t, &local);
			#endif*/
			time_t t = time(nullptr);
			struct tm local;
			TimeUtils::localtime(&t, &local);  // 调用统一接口

			_hour = local.tm_hour;
			_minute = local.tm_min;
			_second = local.tm_sec;
		}
		void tick() {
			Sleep(1000);

			// 每60秒同步一次系统时间，避免累积误差
			if (++_second >= 60) {
				_second = 0;
				if (++_minute >= 60) {
					_minute = 0;
					if (++_hour >= 24) {
						_hour = 0;
					}
				}

				// 每分钟与系统时间同步，减少误差
				if (_second == 0 && _minute % 1 == 0) {
					updateTimeFromSystem();
				}
			}
		}
		void show()
		{
			// 清屏替代方案（更便携）
			//std::cout << std::string(50, '\n');
			system("cls"); // 清屏

			// 格式化输出（确保两位数字）
			std::cout << std::setfill('0')
				<< std::setw(2) << _hour << ":"
				<< std::setw(2) << _minute << ":"
				<< std::setw(2) << _second << std::endl;
		}
};

//int main() {
//	ClockTest clock;
//	clock.runTime();
//	return 0;
//}