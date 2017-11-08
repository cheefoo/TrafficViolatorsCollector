package com.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by temitayo on 10/06/17.
 * **
 * Install library
 * mvn install:install-file -Dfile=/Users/temitayo/Downloads/aws-lambda-java-events-2.0.jar
 * -DgroupId=com.amazonaws
 * -DartifactId=aws-lambda-java-events -Dversion=2.0 -Dpackaging=jar
 */
public class TransformRecordWithNewline
{
     final static CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
     public static void handleRequest(KinesisFirehoseEvent event, Context context)
     {
          LambdaLogger logger = context.getLogger();
          KinesisFirehoseEvent transformedEvent = new KinesisFirehoseEvent();
          List<KinesisFirehoseEvent.Record> records = new ArrayList<>();
          logger.log("Size of records received is  " + event.getRecords().size());
           for(KinesisFirehoseEvent.Record record : event.getRecords())
           {
                try
                {
                     logger.log("Record received is " + record.getData());
                     String decodedRecordData = decoder.decode(record.getData()).toString();
                     logger.log("Decoded Record Data is " + decodedRecordData);
                     logger.log("Adding a new line character");
                     StringBuilder sb = new StringBuilder(decodedRecordData).append("\n");
                     logger.log("transformed Record Data is " + sb.toString());
                     KinesisFirehoseEvent.Record transformedRecord = new KinesisFirehoseEvent.Record();
                     transformedRecord.setRecordId(record.getRecordId());
                     transformedRecord.setData(ByteBuffer.wrap(transformedRecord.toString().getBytes("UTF-8")));
                     records.add(transformedRecord);
                }
                catch (CharacterCodingException e)
                {
                    logger.log("CharacterCodingException enocuntered " + e.toString());
                } catch (UnsupportedEncodingException e)
                {
                     logger.log("UnsupportedEncodingException enocuntered " + e.toString());
                }
           }
          transformedEvent.setRecords(records);
     }

}
