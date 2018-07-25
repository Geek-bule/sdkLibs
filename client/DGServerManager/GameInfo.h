//
//  GameInfo.h
//  sdkIOSDemo
//
//  Created by zyshi on 2017/2/16.
//
//

#import <Foundation/Foundation.h>

@interface GameInfo : NSObject

@property (nonatomic, strong) NSNumber *id;
@property (nonatomic, strong) NSString *code;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSString *mobileType;
@property (nonatomic, strong) NSString *platformId;
@property (nonatomic, strong) NSString *platformUrl;
@property (nonatomic, strong) NSString *platformVersion;
//@property (nonatomic, strong) NSString *isDeleted;
//@property (nonatomic, strong) NSString *gmtCreate;
//@property (nonatomic, strong) NSString *gmtModified;


- (void)setValue:(id)value forUndefinedKey:(NSString *)key;
- (instancetype)initWithDictionary:(NSDictionary *)dictionary;

@end
