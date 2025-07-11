#ifndef BED_H
#define BED_H
class Bed
{
public:
	Bed();
	~Bed();
	void sleep();
};
#endif

#ifndef SOFA_H
#define SOFA_H
class Sofa
{
public:
	Sofa();
	~Sofa();
	void sit();
};
#endif


#ifndef SOFABED_H
#define SOFABED_H
class SofaBed :public Sofa, public Bed
{
public:
	SofaBed();
	~SofaBed();
};
#endif