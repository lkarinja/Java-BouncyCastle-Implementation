/*
Copyright © 2015-2017 Leejae Karinja

This file is part of Java BouncyCastle Implementation.

Java BouncyCastle Implementation is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Java BouncyCastle Implementation is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Java BouncyCastle Implementation.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.net.Inet4Address;

public class Main {

    public static void main(String[] args) {
        new Thread() {

            public void run() {
                Server s = new Server(13579);
                s.start();
                s.sendData("Hello, World!".getBytes());
                s.stop();
            }
        }.start();
        new Thread() {

            public void run() {
                try {
                    Client c = new Client(Inet4Address.getLocalHost().getHostAddress(), 13579);
                    c.start();
                    System.out.println(new String(c.receiveData()));
                    c.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return;
    }
}