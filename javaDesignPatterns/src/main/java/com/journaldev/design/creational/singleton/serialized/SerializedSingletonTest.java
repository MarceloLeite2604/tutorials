package com.journaldev.design.creational.singleton.serialized;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializedSingletonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		SerializedSingleton instanceOne = SerializedSingleton.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instanceOne);
		out.close();

		/* Deserialize from file to object. */
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject();
		in.close();

		/*
		 * If the "readResolve" method is implemented on the serialized singleton, then
		 * the two outputs below will have the same value (the instances are the same).
		 * If it is not implemented, then both outputs will be different, proving that
		 * there is a new singleton instance after its deserialization.
		 */
		System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
		System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());

	}

}