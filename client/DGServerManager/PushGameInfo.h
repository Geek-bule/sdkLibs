//
//  GameInfo.h
//  sdkIOSDemo
//
//  Created by zyshi on 2017/2/16.
//
//

#import <Foundation/Foundation.h>

@interface PushGameInfo : NSObject

@property (nonatomic, strong) NSString *gameCode;
@property (nonatomic, strong) NSString *platformUrl;
@property (nonatomic, strong) NSString *platformVersion;
@property (nonatomic, strong) NSString *platformId;
@property (nonatomic, strong) NSNumber *percent;

- (void)setValue:(id)value forUndefinedKey:(NSString *)key;
- (instancetype)initWithDictionary:(NSDictionary *)dictionary;

@end
