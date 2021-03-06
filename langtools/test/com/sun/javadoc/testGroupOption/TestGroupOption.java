/*
 * Copyright (c) 2003, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug      4924383
 * @summary  Test to make sure the -group option does not cause a bad warning
 *           to be printed.
 * @author   jamieh
 * @library  ../lib
 * @build    JavadocTester
 * @run main TestGroupOption
 */

public class TestGroupOption extends JavadocTester {

    public static void main(String... args) throws Exception {
        TestGroupOption tester = new TestGroupOption();
        tester.runTests();
    }

    @Test
    void test1() {
        //Make sure the warning is not printed when -group is used correctly.
        javadoc("-d", "out-1",
                "-sourcepath", testSrc,
                "-group", "Package One", "pkg1",
                "-group", "Package Two", "pkg2",
                "-group", "Package Three", "pkg3",
                "pkg1", "pkg2", "pkg3");
        checkExit(Exit.OK);

        checkOutput(Output.WARNING, false,
                "-group");
    }

    @Test
    void test2() {
        //Make sure the warning is printed when -group is not used correctly.
        javadoc("-d", "out-2",
                "-sourcepath", testSrc,
                "-group", "Package One", "pkg1",
                "-group", "Package One", "pkg2",
                "-group", "Package One", "pkg3",
                "pkg1", "pkg2", "pkg3");
        checkExit(Exit.OK);

        checkOutput(Output.WARNING, true,
                "-group");

    }
}
