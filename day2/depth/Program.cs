using System;

namespace depth
{
    public class Depth
    {
        //var
        public int hos_pos, ver_pos, aim;

        public Depth()
        {
            this.hos_pos = 0;
            this.ver_pos = 0;
            this.aim = 0;
        }

        public int get_hos_pos()
        {
            return hos_pos;
        }

        public int get_ver_pos()
        {
            return ver_pos;
        }

        public int get_aim()
        {
            return aim;
        }

        private void increase_depth()
        {
            this.ver_pos++;
        }
        private void decrease_depth()
        {
            this.ver_pos--;
        }
        public void forward()
        {
            this.hos_pos++;
        }
        public void backward()
        {
            this.hos_pos--;
        }
        public void down(Boolean part2)
        {
            if (part2)
            {
                increase_aim();
            }
            else
            {
                increase_depth();
            }

        }
        public void up(Boolean part2)
        {
            if (part2)
            {
                decrease_aim();
            }
            else
            {
                decrease_depth();
            }
        }

        public void increase_aim()
        {
            this.aim++;
        }
        public void decrease_aim()
        {
            this.aim--;
        }
        public void get_input(string input, Boolean part2)
        {
            string[] input_tuple = input.Split(' ');
            int times = Int32.Parse(input_tuple[1]);
            string command = input_tuple[0];
            if (command.Equals("up"))
            {
                for (int i = 0; i < times; i++)
                {
                    this.up(part2);
                }
            }
            else if (command.Equals("down"))
            {
                for (int i = 0; i < times; i++)
                {
                    this.down(part2);
                }
            }
            else if (command.Equals("forward"))
            {
                for (int i = 0; i < times; i++)
                {
                    this.forward();
                }
                if (part2)
                {
                    int depth_increase = this.aim * times;
                    for (int i = 0; i < depth_increase; i++)
                    {
                        this.increase_depth();
                    }

                }
            }
            else if (input_tuple[0].Equals("backward"))
            {
                for (int i = 0; i < times; i++)
                {
                    this.backward();
                }
            }
            else
            {
                Console.WriteLine("Could not read:" + input_tuple[0] + " " + input_tuple[1]);
            }
        }

        public void run(Boolean part2)
        { //set your own placement
            foreach (string line in System.IO.File.ReadLines("file"))
            {
                get_input(line, part2);
            }
            Console.WriteLine("horizontal position: " + this.get_hos_pos());
            Console.WriteLine("Vertical position: " + this.get_ver_pos());
            Console.WriteLine("aim: " + this.get_aim());
            if (part2)
            {
                Console.WriteLine("solution part 2: " + this.get_hos_pos() * this.get_ver_pos());
            }
            else
            {
                Console.WriteLine("solution part 1: " + this.get_hos_pos() * this.get_ver_pos());
            }
        }

        static void Main(string[] args)
        {
            Depth depth1 = new Depth();
            depth1.run(false);
            Depth depth2 = new Depth();
            depth2.run(true);


        }
    }
}
