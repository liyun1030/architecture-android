#include <iostream>
#include <iomanip>
#include <ctime>
#include <chrono>
#include <thread>
#include <string>

#ifdef _WIN32
#include <windows.h>  // Windows ϵͳ
#else
#include <unistd.h>   // POSIX ϵͳ (Linux/Mac)
#define Sleep(ms) usleep((ms) * 1000)  // ��ƽ̨ Sleep ʵ��
#endif
using namespace std;
// ��ƽ̨ʱ��ת��������
class TimeUtils {
public:
	static void localtime(const time_t* t, struct tm* result) {
#ifdef _WIN32
		if (localtime_s(result, t) != 0) {
			throw std::runtime_error("localtime_s failed");
		}
#else
		localtime_r(t, result);  // POSIX ϵͳ��֤�ɹ�
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
			TimeUtils::localtime(&t, &local);  // ����ͳһ�ӿ�

			_hour = local.tm_hour;
			_minute = local.tm_min;
			_second = local.tm_sec;
		}
		void tick() {
			Sleep(1000);

			// ÿ60��ͬ��һ��ϵͳʱ�䣬�����ۻ����
			if (++_second >= 60) {
				_second = 0;
				if (++_minute >= 60) {
					_minute = 0;
					if (++_hour >= 24) {
						_hour = 0;
					}
				}

				// ÿ������ϵͳʱ��ͬ�����������
				if (_second == 0 && _minute % 1 == 0) {
					updateTimeFromSystem();
				}
			}
		}
		void show()
		{
			// �����������������Я��
			//std::cout << std::string(50, '\n');
			system("cls"); // ����

			// ��ʽ�������ȷ����λ���֣�
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