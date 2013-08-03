bin=bin
obj=obj
src=src

compiler=g++

flags=-std=c++11

binary=hgbg

objects:=$(patsubst $(src)/%.cpp,$(obj)/%.o,$(wildcard $(src)/*.cpp))

all: $(objects)
	$(compiler) -o $(bin)/$(binary) $(objects) $(flags)

$(objects): $(wildcard $(src)/*.cpp)
	$(compiler) -c -o $(objects) $(wildcard $(src)/*.cpp) $(flags)
